package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;
import com.example.efkon.view.WalletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Repository
public class WalletDaoImpl implements WalletDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<WalletResponse> getNoOfWallets() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) counts,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }


    public List<WalletResponse> getNoOfWalletsByDate() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) counts,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and sm_wallet.SM_CUST_ACT_DT LIKE '2018-12%' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }
}
