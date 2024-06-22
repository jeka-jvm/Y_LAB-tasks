package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WorkspaceDaoImplTest {

    private WorkspaceDaoImpl workspaceDao;

    @BeforeEach
    public void setUp() {
        workspaceDao = new WorkspaceDaoImpl();
    }

    @Test
    public void testAddWorkspace() {
        Workspace workspace = new Workspace(1, "Workspace 1", true);

        workspaceDao.addWorkspace(workspace);

        List<Workspace> allWorkspaces = workspaceDao.getAllWorkspaces();
        assertEquals(1, allWorkspaces.size());
        assertEquals(workspace, allWorkspaces.get(0));
    }

    @Test
    public void testUpdateExistingWorkspace() throws ResourceNotFoundException {
        Workspace existingWorkspace = new Workspace(1, "Workspace 1", true);
        workspaceDao.addWorkspace(existingWorkspace);

        Workspace updatedWorkspace = new Workspace(1, "Updated Workspace 1", false);

        workspaceDao.updateWorkspace(updatedWorkspace);

        Workspace retrievedWorkspace = workspaceDao.getWorkspaceById(1);
        assertEquals("Updated Workspace 1", retrievedWorkspace.getName());
        assertEquals(false, retrievedWorkspace.isAvailable());
    }

    @Test
    public void testUpdateNonExistingWorkspace() {
        Workspace updatedWorkspace = new Workspace(1, "Updated Workspace 1", false);

        assertThrows(ResourceNotFoundException.class, () -> {
            workspaceDao.updateWorkspace(updatedWorkspace);
        });
    }

    @Test
    public void testDeleteWorkspace() {
        Workspace workspace1 = new Workspace(1, "Workspace 1", true);
        Workspace workspace2 = new Workspace(2, "Workspace 2", false);
        workspaceDao.addWorkspace(workspace1);
        workspaceDao.addWorkspace(workspace2);

        workspaceDao.deleteWorkspace(1);

        assertEquals(1, workspaceDao.getAllWorkspaces().size());
        assertEquals(workspace2, workspaceDao.getAllWorkspaces().get(0));
    }

    @Test
    public void testGetWorkspaceById() {
        Workspace workspace1 = new Workspace(1, "Workspace 1", true);
        Workspace workspace2 = new Workspace(2, "Workspace 2", false);
        workspaceDao.addWorkspace(workspace1);
        workspaceDao.addWorkspace(workspace2);

        Workspace retrievedWorkspace = workspaceDao.getWorkspaceById(1);

        assertEquals(workspace1, retrievedWorkspace);

        retrievedWorkspace = workspaceDao.getWorkspaceById(3);

        assertNull(retrievedWorkspace);
    }

    @Test
    public void testGetAllWorkspaces() {
        Workspace workspace1 = new Workspace(1, "Workspace 1", true);
        Workspace workspace2 = new Workspace(2, "Workspace 2", false);
        workspaceDao.addWorkspace(workspace1);
        workspaceDao.addWorkspace(workspace2);

        List<Workspace> allWorkspaces = workspaceDao.getAllWorkspaces();

        assertEquals(2, allWorkspaces.size());
        assertEquals(workspace1, allWorkspaces.get(0));
        assertEquals(workspace2, allWorkspaces.get(1));
    }
}
