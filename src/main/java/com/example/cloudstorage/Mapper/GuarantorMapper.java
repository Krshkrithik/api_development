package com.example.cloudstorage.Mapper;

import com.example.cloudstorage.dto.GuarantorSignup;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GuarantorMapper {

    @Update("update customer set is_guarantor = #{status},guarantor_count = #{count} where id = #{cust_id}")
    void updateVerification(@Param("cust_id") int cust_id,@Param("status") boolean status,@Param("count") int count);

    @Insert("insert into guarantor(email) values (#{email})")
    void createGuarantor(String email);

    @Update("update guarantor set name=#{name},password=#{password},mobile_number=#{mobileNumber},gender=#{gender},is_active=true,relationship=#{relationship},created_at = now(),updated_at=now() where email = #{emailId}")
    void updateGuarantor(GuarantorSignup signup);

    @Select("Select guarantor_count from customer where id=#{cust_id}")
    int getGuarantorCount(int cust_id);

    @Select("select count(*) from guarantor where email=#{email}")
    int countByEmail(String email);


    @Insert("insert into guarantor_documents (document_name,document_type,guarantor_id) values (#{documentName},#{documentType},#{guarantorId})")
    void uploadDocuments(String documentName,String documentType,int guarantorId);

    @Select("select count(*) from guarantor_documents where guarantor_id=#{id}")
    int count(int id);

}
