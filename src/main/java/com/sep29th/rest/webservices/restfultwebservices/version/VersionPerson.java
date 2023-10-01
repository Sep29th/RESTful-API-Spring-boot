package com.sep29th.rest.webservices.restfultwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPerson {

	// Version with URI
	@GetMapping(path = "/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/v2/person")
	public PersonV2 getSecondVersionPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Version with parameters
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionPersonByParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	//Version with header
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionPersonByHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	//Version with accept
	@GetMapping(path = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionPersonByAccpet() {
		return new PersonV1("Bob Charlie");
	}
}
