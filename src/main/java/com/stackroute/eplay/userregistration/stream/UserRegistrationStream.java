package com.stackroute.eplay.userregistration.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserRegistrationStream {
	String OUTPUT = "user-registration-out";

	@Output(OUTPUT)
	MessageChannel outboundUserRegistration();
}