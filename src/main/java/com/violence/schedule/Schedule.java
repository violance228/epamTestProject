package com.violence.schedule;

import com.violence.repository.BookRepository;
import com.violence.repository.BookRepositoryImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private BookRepository bookRepository = new BookRepositoryImpl();

    public void rebuildBooks() {
        scheduler.scheduleAtFixedRate(() -> bookRepository.rebuildByDate(), 10, 24, TimeUnit.HOURS);
    }
}
