package org.coworking.service;

import org.coworking.dao.WorkspaceDao;
import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;

import java.util.List;


/**
 * Реализация сервиса для управления рабочими пространствами.
 */
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceDao workspaceDao;

    /**
     * Конструктор для инициализации сервиса с указанием DAO для рабочих пространств.
     *
     * @param workspaceDao DAO для взаимодействия с данными рабочих пространств
     */
    public WorkspaceServiceImpl(WorkspaceDao workspaceDao) {
        this.workspaceDao = workspaceDao;
    }

    @Override
    public void addWorkspace(String name, boolean available) {
        Workspace workspace = new Workspace(workspaceDao.getAllWorkspaces().size() + 1, name, available);
        workspaceDao.addWorkspace(workspace);
    }

    @Override
    public void updateWorkspace(int id, String name, boolean available) throws ResourceNotFoundException {
        Workspace existingWorkspace = workspaceDao.getWorkspaceById(id);
        if (existingWorkspace == null) {
            throw new ResourceNotFoundException("Workspace not found");
        }
        Workspace updatedWorkspace = new Workspace(id, name, available);
        workspaceDao.updateWorkspace(updatedWorkspace);
    }

    @Override
    public void deleteWorkspace(int id) throws ResourceNotFoundException {
        Workspace workspace = workspaceDao.getWorkspaceById(id);
        if (workspace == null) {
            throw new ResourceNotFoundException("Workspace not found");
        }
        workspaceDao.deleteWorkspace(id);
    }

    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceDao.getAllWorkspaces();
    }
}
