package edu.wctc.bakeryjpa.jsf;

import edu.wctc.bakeryjpa.ejb.MenuFacade;
import edu.wctc.bakeryjpa.ejb.OrderDetailFacade;
import edu.wctc.bakeryjpa.ejb.OrderHeaderFacade;
import edu.wctc.bakeryjpa.entities.Menu;
import edu.wctc.bakeryjpa.entities.OrderDetail;
import edu.wctc.bakeryjpa.entities.OrderHeader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Teresa Mahoney
 */
@Named
@SessionScoped
public class MenuBean implements Serializable {

    private List<Menu> menuItems;
    private List<Menu> selectedItems;
    private Menu singleMenu;
    @Inject
    private MenuFacade menuFacade;
    @Inject
    private OrderHeaderFacade ohFacade;
    @Inject
    private OrderDetailFacade odFacade;

    public MenuBean() {
    }

    @PostConstruct
    public void init() {
        menuItems = menuFacade.findAll();
    }

    public String processOrder() {
        OrderHeader header = new OrderHeader();
        header.setOrderDate(new Date());
        header = ohFacade.saveNew(header);
        List<OrderDetail> details = new ArrayList<OrderDetail>();
        for (Menu m : selectedItems) {
            OrderDetail od = new OrderDetail();
            od.setMenuId(m);
            od.setOrderHeaderId(header);
            od.setOrderDetailQty(1);
            
            details.add(od);
        }
        header.setOrderDetailCollection(details);
        ohFacade.edit(header);
        return "ordersummary";
    }

    public List<Menu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Menu> menuItems) {
        this.menuItems = menuItems;
    }

    public Menu getSingleMenu() {
        return singleMenu;
    }

    public void setSingleMenu(Menu singleMenu) {
        this.singleMenu = singleMenu;
    }

    public MenuFacade getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    public List<Menu> getSelectedItems() {
        return selectedItems;
    }

    public OrderHeaderFacade getOhFacade() {
        return ohFacade;
    }

    public void setOhFacade(OrderHeaderFacade ohFacade) {
        this.ohFacade = ohFacade;
    }

    public OrderDetailFacade getOdFacade() {
        return odFacade;
    }

    public void setOdFacade(OrderDetailFacade odFacade) {
        this.odFacade = odFacade;
    }

    public void setSelectedItems(List<Menu> selectedItems) {
        this.selectedItems = selectedItems;
    }
    public void onEdit(RowEditEvent event){
        FacesMessage msg = null;
        
        try {
             this.getMenuFacade().edit(singleMenu);
        
            msg = new FacesMessage("Staff Edited Successfully", event.getObject().toString()); 
            
        } catch(Exception e) {
            msg = new FacesMessage("Record in use, please try again later", ""); 
        } finally {
            //loadStores();
        }
         
        FacesContext.getCurrentInstance().addMessage(null, msg);  
      
       
    }
    public void onCancel(){
        
    }
    

}
