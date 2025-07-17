package com.arg.user.user.services;

import com.arg.user.user.dto.EmployeeDto;
import com.arg.user.user.entities.Contact;

public interface ContactService {

    Contact saveContact(EmployeeDto contact);
}
