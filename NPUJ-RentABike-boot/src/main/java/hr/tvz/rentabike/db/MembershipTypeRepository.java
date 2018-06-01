package hr.tvz.rentabike.db;

import java.util.List;

import hr.tvz.rentabike.model.MembershipType;;

public interface MembershipTypeRepository {

	List<MembershipType> findAll();

	MembershipType findOne(String id);

	MembershipType save(MembershipType m);
	
	void deleteMembershipType(String id);

}