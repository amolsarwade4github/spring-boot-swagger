package com.example.swagger.resources;

import com.example.swagger.model.Contact;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(
            value = "Finds Contact by id",
            notes = "Provide an id to look up specific Contact from the address book",
            response = Contact.class
    )
    public Contact getContact(
            @ApiParam(value = "ID value for the Contact you need to retrive", required = true)
            @PathVariable("id") String id) {
        return contacts.get(id);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contacts.put(contact.getId(), contact);
    }

}
