package Service;

import DAO.FichierUtiliseDAO;
import Model.User;

/**
 * Created by hadjiszs on 21/10/16.
 */
public class FileService {
    FichierUtiliseDAO dao;

    public FileService() {
        dao = new FichierUtiliseDAO(APIService.em);
    }

    public String get(Long idProject, String idFichier) {
        // @TODO fonction get file
        return null;
    }

    // id -> id hexa corresponodant à l'ObjectID
    public void edit(User u, String id, String contenue) {
        APIService.em.getTransaction().begin();
        dao.createOrUpdate(u, id, contenue);
        APIService.em.getTransaction().commit();
    }
}
