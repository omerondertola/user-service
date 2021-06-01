package tr.com.tolasoft.spring.sample.userservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.tolasoft.spring.sample.userservice.VO.Department;
import tr.com.tolasoft.spring.sample.userservice.VO.ResponseTemplateVO;
import tr.com.tolasoft.spring.sample.userservice.entity.User;
import tr.com.tolasoft.spring.sample.userservice.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService"+user);
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService"+userId);
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
        //Department department = restTemplate.getForObject(
        // "http://localhost:9001/departments/"+user.getDepartmentId(), Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
