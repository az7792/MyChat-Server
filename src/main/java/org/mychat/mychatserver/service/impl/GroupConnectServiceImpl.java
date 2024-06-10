package org.mychat.mychatserver.service.impl;

import org.mychat.mychatserver.mapper.GroupConnectMapper;
import org.mychat.mychatserver.service.GroupConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupConnectServiceImpl implements GroupConnectService {

    @Autowired
    private final GroupConnectMapper groupConnectMapper;

    public GroupConnectServiceImpl(GroupConnectMapper groupConnectMapper) {
        this.groupConnectMapper = groupConnectMapper;
    }


    @Override
    public List<Integer> getAllUidByGroupId(Integer groupId) {
        return groupConnectMapper.getAllUidBygroupid(groupId);
    }
}
