package com.example.softwareproject;


import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.UserRepository;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareProjectApplicationTests {
    @Autowired
    private ExamService examService;
    @Autowired
    private TimeUtils timeUtils;
    @Autowired
    private InitService initService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1() {
        User u4 = new User("test","9999","123456",User.USER_AUTHORITY);
        userRepository.save(u4);
    }

    @Test
    public void init() {
        initService.init_Exam();
    }


    @Test
    public void min_max_Time_test() {
        LocalDateTime t1 = LocalDateTime.of(2019, 6, 15, 9, 10);
        LocalDateTime t2 = LocalDateTime.of(2019, 6, 16, 10, 10);
        log.debug(timeUtils.maxTime(t1, t2).toString() );
        log.debug(timeUtils.minTime(t1, t2).toString() );
        log.debug(t1.compareTo(t2) + "\n" +  t2.compareTo(t1) + "\n" +  t1.compareTo(t1));
    }
    @Test
    public void isTimeConflict_test() {
        Exam e1 = new Exam();
        Exam e2 = new Exam();
        e1.setBeginTime(LocalDateTime.of(2019,6, 12, 5, 0));
        e1.setEndTime(LocalDateTime.of(2019,6, 12, 7, 0));
        e2.setBeginTime(LocalDateTime.of(2019,6, 12, 7, 0));
        e2.setEndTime(LocalDateTime.of(2019,6, 12, 8, 0));
        log.debug(timeUtils.isTimeConflict(e1, e2) + "");
    }
    

}
