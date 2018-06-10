package com.stage.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.stage.dao.DAOException;
import com.stage.dao.PersonnelDao;

@ManagedBean
@RequestScoped
public class ExistenceIDPersonnelValidator implements Validator {

	private static final String PERSONNEL_NONEXISTANT = "Pas de personnel avec cette ID";
	
	@EJB
    private PersonnelDao      personnelDao;
	
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        
        long id = (long) value;
        try {
            if ( personnelDao.trouver( id ) == null ) {
                
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, PERSONNEL_NONEXISTANT, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}
