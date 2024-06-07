package org.mychat.mychatserver.service;


import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupConnectService {
    List<Integer> getAllUidByGroupId(Integer groupId);
}
