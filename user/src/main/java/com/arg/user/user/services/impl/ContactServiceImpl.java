package com.arg.user.user.services.impl;

import com.arg.user.user.dto.EmployeeDto;
import com.arg.user.user.entities.Contact;
import com.arg.user.user.respositories.ContactRepository;
import com.arg.user.user.services.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Contact saveContact(EmployeeDto dto) {
        Contact contact = new Contact();
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        return contactRepository.save(contact);
    }
}
