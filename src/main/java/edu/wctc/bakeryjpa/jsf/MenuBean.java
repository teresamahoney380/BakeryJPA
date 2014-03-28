package edu.wctc.bakeryjpa.jsf;

import edu.wctc.bakeryjpa.ejb.MenuFacade;
import edu.wctc.bakeryjpa.entities.Menu;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    MenuFacade menuFacade;

    public MenuBean() {
    }

    @PostConstruct
    public void init() {
        menuItems = menuFacade.findAll();
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

    public void setSelectedItems(List<Menu> selectedItems) {
        this.selectedItems = selectedItems;
    }

}
