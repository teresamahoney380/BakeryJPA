/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wctc.bakeryjpa.ejb;

import edu.wctc.bakeryjpa.entities.OrderHeader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Owner
 */
@Stateless
public class OrderHeaderFacade extends AbstractFacade<OrderHeader> {
    @PersistenceContext(unitName = "edu.wctc_BakeryJPA_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderHeaderFacade() {
        super(OrderHeader.class);
    }
    
    public OrderHeader saveNew(OrderHeader entity) {
        return getEntityManager().merge(entity);
    }
    
}
