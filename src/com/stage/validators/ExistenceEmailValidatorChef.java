package com.stage.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;


import com.stage.dao.ChefProjetDao;
import com.stage.dao.DAOException;


@ManagedBean
@RequestScoped
public class ExistenceEmailValidatorChef implements Validator {
	
	private static final String EMAIL_EXISTE_DEJA = "Cette adresse email est déjà utilisée";
	
	@EJB
    private ChefProjetDao      chefDao;
   
	
    @Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        
        String email = (String) value;
        try {
            if ( chefDao.trouver( email ) != null ) {
                
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, EMAIL_EXISTE_DEJA, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }


}
