package com.database.demo;

import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UsersService {

    HashMap<LocalDate, RoaringBitmap> registeredIndexes = new HashMap<>();
    HashMap<String, RoaringBitmap> groupIndexes = new HashMap<>();
    TreeMap<Integer, User> usersMap = new TreeMap<Integer, User>();

    public User getUserById(long id) {
        return usersMap.get(id);
    }

    public void saveUser(User user) {
        registeredIndexes.computeIfAbsent(user.getCreatedAt().toLocalDate(), k -> RoaringBitmap.bitmapOf()).add(user.getId());
        user.getGroups().forEach(group -> {
            groupIndexes.computeIfAbsent(group, k -> RoaringBitmap.bitmapOf()).add(user.getId());
        });
        usersMap.put(user.getId(), user);
    }



}
