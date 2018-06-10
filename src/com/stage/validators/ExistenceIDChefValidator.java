package com.stage.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.stage.dao.ChefProjetDao;
import com.stage.dao.DAOException;

@ManagedBean
@RequestScoped
public class ExistenceIDChefValidator implements Validator {

	private static final String CHEF_NONEXISTANT = "Pas de chef avec cette ID";
	
	@EJB
    private ChefProjetDao      chefDao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        
        long id = (long) value;
        try {
            if ( chefDao.trouver( id ) == null ) {
                
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, CHEF_NONEXISTANT, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
	
}
