package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.constants.FileType;
import com.ps.ElectronicsStore.dto.ConnectionTimeDTO;
import com.ps.ElectronicsStore.dto.ConnectionTimeDTOList;
import com.ps.ElectronicsStore.dto.ConnectionTimeUpdateDTO;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.ConnectionTime;
import com.ps.ElectronicsStore.model.User;
import com.ps.ElectronicsStore.repository.ConnectionTimeRepository;
import com.ps.ElectronicsStore.service.ConnectionTimeService;
import com.ps.ElectronicsStore.utils.exporter.FileExporter;
import com.ps.ElectronicsStore.utils.exporter.TXTFileExporter;
import com.ps.ElectronicsStore.utils.exporter.XMLFileExporter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConnectionTimeServiceImpl implements ConnectionTimeService {
    private final ConnectionTimeRepository connectionTimeRepository;

    public ConnectionTimeServiceImpl(ConnectionTimeRepository connectionTimeRepository) {
        this.connectionTimeRepository = connectionTimeRepository;
    }

    @Override
    @Transactional
    public ConnectionTime save(ConnectionTime connectionTime) {
        return connectionTimeRepository.save(connectionTime);
    }

    @Override
    @Transactional
    public ConnectionTime updateLogout(ConnectionTimeUpdateDTO dto) {
        List<ConnectionTime> connectionTimes = connectionTimeRepository.findAll();


        for (ConnectionTime connectionTime1 : connectionTimes) {
            User user = connectionTime1.getUser();
            if (user.getId() == dto.getId() && (connectionTime1.getFinished() == false)) {
                ConnectionTime connectionTime = connectionTime1;
                connectionTime.setLogout(new Date());
                connectionTime.setFinished(true);
                return connectionTimeRepository.save(connectionTime);
            }
        }
        return null;
    }


    @Override
    public List<ConnectionTimeDTO> findAll() {
        List<ConnectionTime> connectionTimes = connectionTimeRepository.findAll();
        List<ConnectionTimeDTO> rezult = new ArrayList<>();

        for (ConnectionTime cT : connectionTimes) {
            User client = cT.getUser();
            rezult.add(new ConnectionTimeDTO(cT.getId(), cT.getLogin(), cT.getLogout(), cT.getFinished(), client.getUsername()));
        }
        return rezult;
    }

    @Override
    public String exportTimeStamps(String fileType) {
        List<ConnectionTimeDTO> connectionTimes = this.findAll();
        String rezult = "";
        FileExporter fileExporter;
        if (fileType.equals(FileType.XML)) {
            fileExporter = new XMLFileExporter();
            for (ConnectionTimeDTO connectionTimeDTO : connectionTimes) {
                rezult = rezult + fileExporter.exportData(connectionTimeDTO) + "\n";
            }
            return rezult;
        } else if (fileType.equals(FileType.TXT)) {
            fileExporter = new TXTFileExporter();
            for (ConnectionTimeDTO connectionTimeDTO : connectionTimes) {
                rezult = rezult + fileExporter.exportData(connectionTimeDTO) + "\n";
            }
            return rezult;
        }
        return null;

    }
}
