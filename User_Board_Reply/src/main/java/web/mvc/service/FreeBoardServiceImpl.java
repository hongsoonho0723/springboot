package web.mvc.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.mvc.domain.FreeBoard;
import web.mvc.repository.FreeboardRepository;

import java.util.List;
@Service
public class FreeBoardServiceImpl implements FreeBoardService {


    @Autowired
    private FreeboardRepository freeboardRepository;

    @Override
    public List<FreeBoard> selectAll() {
    List<FreeBoard> list = freeboardRepository.selectAll();

        return list;
    }

    @Override
    public Page<FreeBoard> selectAll(Pageable pageable) {
        return null;
    }

    @Override
    public void insert(FreeBoard board) {

    }

    @Override
    public FreeBoard selectBy(Long bno, boolean state) {
        return null;
    }

    @Override
    public FreeBoard update(FreeBoard board) {
        return null;
    }

    @Override
    public void delete(Long bno, String password) {

    }
}
