package com.example.swagger.resources;

import com.example.swagger.model.Contact;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/contacts")
public class AddressBookResource {

    ConcurrentHashMap<String, Contact> contacts = new ConcurrentHashMap<>();

    @GetMapping
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    @GetMapping(path = "{id}")
    public Contact getContact(@PathVariable("id") String id) {
        return contacts.get(id);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contacts.put(contact.getId(), contact);
    }

}
