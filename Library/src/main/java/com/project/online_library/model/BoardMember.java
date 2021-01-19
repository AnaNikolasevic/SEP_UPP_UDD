package com.project.online_library.model;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import com.project.online_library.enums.WriterMembershipStatus;


@Entity
public class BoardMember extends Users{
	@ElementCollection
    @CollectionTable(name = "writer_membership_status", 
      joinColumns = {@JoinColumn(name = "board_member_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "writer_username")
    @Column(name = "status")
	Map<String, WriterMembershipStatus> writerMembership = new HashMap<String, WriterMembershipStatus>();
	
    public BoardMember(){}


    public BoardMember(String firstName, String lastName, String email, String password, String username, String city, String country, boolean enabled) {
        super(firstName, lastName, email, password, username, city, country, enabled);
    }


	public Map<String, WriterMembershipStatus> getWriterMembership() {
		return writerMembership;
	}


	public void setWriterMembership(Map<String, WriterMembershipStatus> writerMembership) {
		this.writerMembership = writerMembership;
	}
    
    
}
