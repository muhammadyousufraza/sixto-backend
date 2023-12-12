package com.example.practice.project.utilities;

import com.example.practice.project.customexception.BusinessException;
import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import com.example.practice.project.model.request.UserAddRequest;
import com.example.practice.project.model.request.UserUpdateRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;

public class ModelConverter {


    private ModelConverter() {
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public static UserDto convertToDto(UserAddRequest userAddRequest) {
        return modelMapper.map(userAddRequest, UserDto.class);
    }

    public static UserDto convertToDto(UserUpdateRequest userUpdateRequest) {
        return modelMapper.map(userUpdateRequest, UserDto.class);
    }

    public static List<UserDto> convertToUserDtosList(List<User> users) {
        return Arrays.asList(modelMapper.map(users, UserDto[].class));
    }

    public static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    /**
     * convertToMap.
     *
     * @param obj obj
     * @return Map
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
                throw new BusinessException(e.getLocalizedMessage());
            }
        }
        return map;
    }
}
