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
import java.util.List;

@Repository
public class TagsDaoImpl implements TagsDao {


    @PersistenceContext
    private EntityManager entityManager;

    public List<TagResponse> getNoOfTags() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_card.[TAG_ID]) counts,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }

    @Override
    public List<?> getNoOfTagsByDate() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_card.[TAG_ID]) counts,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where  sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_card.SM_DT_ISSUE LIKE '2018-12%' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TagResponse.class))
                .list();
    }
}
