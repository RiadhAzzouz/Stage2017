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
import com.stage.dao.ProjetDao;


@ManagedBean
@RequestScoped
public class ExistenceProjetValidator implements Validator {

	private static final String NOM_EXISTE_DEJA = "Ce projet existe deja";

    @EJB
    private ProjetDao      projetDao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        
        String nom = (String) value;
        try {
            if ( projetDao.trouver( nom ) != null ) {
                
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, NOM_EXISTE_DEJA, null ) );
            }
        } catch ( DAOException e ) {
           
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
	
	
}
