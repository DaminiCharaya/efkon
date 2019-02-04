package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;
import org.apache.catalina.webresources.ClasspathURLStreamHandler;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class TagsDaoImpl implements TagsDao {


    @PersistenceContext
    private EntityManager entityManager;

    public List<TagResponse> fetchTagCountByCustomerTypeAndGroupByStatus(Integer customerType) {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }


    public List<TagResponse>fetchTagCountGroupByCustomerType() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<?> fetchTagCountByDateGroupByCustomerType(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
       String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where  sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_card.SM_DT_ISSUE LIKE '%"+ changedDate +"%' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();







    }

    @Override
    public List<?> fetchTagCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_cust.CUST_TYPE=:CUST_TYPE and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_card.SM_DT_ISSUE LIKE '%"+ changedDate +"%' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();

    }

    @Override
    public List<?> noOfTagsIssuedInGivenDates() {
        return null;
    }
}
