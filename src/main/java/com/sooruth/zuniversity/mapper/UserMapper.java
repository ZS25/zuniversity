package com.sooruth.zuniversity.mapper;

import com.sooruth.zuniversity.entity.User;
import com.sooruth.zuniversity.record.UserRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRecordToUser(UserRecord userRecord);
    UserRecord userToUserRecord(User user);

    List<User> listUserRecordsToListUsers(List<UserRecord> userRecordList);
    List<UserRecord> listUsersToListUserRecords(List<User> userList);
}
