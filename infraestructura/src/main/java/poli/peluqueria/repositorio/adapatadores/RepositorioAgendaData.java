package poli.peluqueria.repositorio.adapatadores;

import org.springframework.stereotype.Repository;
import poli.peluqueria.modelo.Agenda;
import poli.peluqueria.puerto.repositorio.RepositorioAgenda;
import poli.peluqueria.repositorio.builder.MaperAgenda;
import poli.peluqueria.repositorio.crudrepository.CrudAgendaRepository;
import poli.peluqueria.repositorio.entidades.EntidadAgenda;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RepositorioAgendaData implements RepositorioAgenda {

    CrudAgendaRepository crudAgendaRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    public RepositorioAgendaData(CrudAgendaRepository crudAgendaRepository) {
        this.crudAgendaRepository = crudAgendaRepository;
    }

    @Override
    public void crear(Agenda agenda) {

        EntidadAgenda entidadAgenda = this.crudAgendaRepository
                .findByCedulaCliente(agenda.getCedulaCliente());
        if (entidadAgenda == null) {
            this.crudAgendaRepository.save(MaperAgenda.convertirAEntidad(agenda));
        }
    }

    @Override
    public Agenda obtener(String cedulaCliente) {
        EntidadAgenda entidadAgenda = this.crudAgendaRepository.findByCedulaCliente(cedulaCliente);
        return MaperAgenda.convertirAModelo(entidadAgenda);
    }

    @Override
    public void delete(int id) {
        EntidadAgenda entidadAgenda = get(id);
        entityManager.remove(entidadAgenda);
    }
}
