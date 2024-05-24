package web.mvc.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.User;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.UserRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *  user안에 userId를 가져와서 해당 데이터가 DB에 객체 반환 없으면 null
     *  null 일때 예외 발생
     * */
    @Transactional
    @Override
    public User loginCheck(User user) throws BasicException {

        log.info("loginCheck userID={}, pwd={}",user.getUserId(),user.getPwd());

/*
     User u = userRepository.findById(user.getUserId()).orElse(null);

        if(u == null) throw new BasicException(ErrorCode.NOTFOUND_ID);

        log.info("loginCheck userID={}, pwd={}",u.getUserId(),u.getPwd());
*/

        User dbUser = userRepository.findById(user.getUserId()).orElseThrow(()-> new BasicException(ErrorCode.NOTFOUND_ID));

        //비번검증
        if(!dbUser.getPwd().equals(user.getPwd())){
            throw new BasicException(ErrorCode.WRONG_PASS);
        }
        log.info("loginCheck userID={}, pwd={}",dbUser.getUserId(),dbUser.getPwd());

        return dbUser;
    }





}
