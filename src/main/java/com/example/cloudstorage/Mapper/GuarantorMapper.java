package com.example.cloudstorage.Mapper;

import com.example.cloudstorage.dto.GuarantorSignup;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GuarantorMapper {

    @Update("update customer set is_guarantor = #{status},guarantor_count = #{count} where id = #{cust_id}")
    void updateVerification(@Param("cust_id") int cust_id,@Param("status") boolean status,@Param("count") int count);

    @Insert("insert into guarantor(customer_id) values (#{cust_id})")
    void createGuarantor(int cust_id);

    @Update("insert into guarantor (name,email,mobile_number,password) values (#{name},#{emailId},#{mobileNumber},#{password})")
    void updateGuarantor(GuarantorSignup signup);

    @Select("Select guarantor_count from guarantor where id=cust_id")
    int getGuarantorCount(int cust_id);
}
