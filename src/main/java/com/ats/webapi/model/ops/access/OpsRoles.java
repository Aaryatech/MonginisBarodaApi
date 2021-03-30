package com.ats.webapi.model.ops.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ops_roles")
public class OpsRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_json")
	private String roleJson;
	
	@Column(name="del_status")
	private int delStatus;

	@Override
	public String toString() {
		return "OpsRoles [roleId=" + roleId + ", roleName=" + roleName + ", roleJson=" + roleJson + ", delStatus="
				+ delStatus + "]";
	}
	
	
	
}
