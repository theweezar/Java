package app.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
public class MailController {
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping("send")
	public String send(ModelMap model){
		try{
			String from = "hpmduc1999@gmail.com";
			String to = "minhducducminh1999@gmail.com";
			String subject = "Activation Music Web";
			String body = "Activation code ....";
			MimeMessage mail = mailer.createMimeMessage() ;
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from,from);
			helper.setTo(to);
			helper.setReplyTo(from,from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			model.addAttribute("message","Gửi email thành công!");
		}catch(Exception ex){
			model.addAttribute("message","Gửi email thất bại");
		}
		return "mail/mail";
	}
}
