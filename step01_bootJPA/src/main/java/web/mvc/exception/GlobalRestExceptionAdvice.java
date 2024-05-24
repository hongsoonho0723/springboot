package web.mvc.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice // @ControllerAdviced @ResponseBody
@Slf4j
public class GlobalRestExceptionAdvice {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> error(IllegalArgumentException e) {
		log.error("예외메시지 : {} " , e.getMessage());

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type","application/json;charset=UTF-8");
		return new ResponseEntity<>(e.getMessage(), resHeaders, HttpStatus.NOT_FOUND); //HttpStatus.NOT_FOUND는 404에러


	}
}





























