package org.coworking.service;

import org.coworking.dao.WorkspaceDao;
import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.Workspace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class WorkspaceServiceImplTest {

    private WorkspaceDao workspaceDao;
    private WorkspaceService workspaceService;

    @BeforeEach
    public void setUp() {
        workspaceDao = Mockito.mock(WorkspaceDao.class);
        workspaceService = new WorkspaceServiceImpl(workspaceDao);
    }

    @Test
    public void testAddWorkspace() {
        String name = "Workspace 1";
        boolean available = true;
        Workspace workspace = new Workspace(1, name, available);

        workspaceService.addWorkspace(name, available);

        verify(workspaceDao, times(1)).addWorkspace(workspace);
    }

    @Test
    public void testUpdateExistingWorkspace() throws ResourceNotFoundException {
        int id = 1;
        String newName = "Updated Workspace";
        boolean newAvailable = false;
        Workspace existingWorkspace = new Workspace(id, "Workspace 1", true);
        when(workspaceDao.getWorkspaceById(id)).thenReturn(existingWorkspace);

        workspaceService.updateWorkspace(id, newName, newAvailable);

        verify(workspaceDao, times(1)).updateWorkspace(new Workspace(id, newName, newAvailable));
    }

    @Test
    public void testUpdateNonExistingWorkspace() {
        int id = 1;
        String newName = "Updated Workspace";
        boolean newAvailable = false;
        when(workspaceDao.getWorkspaceById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            workspaceService.updateWorkspace(id, newName, newAvailable);
        });
    }

    @Test
    public void testDeleteExistingWorkspace() throws ResourceNotFoundException {
        int id = 1;
        Workspace existingWorkspace = new Workspace(id, "Workspace 1", true);
        when(workspaceDao.getWorkspaceById(id)).thenReturn(existingWorkspace);

        workspaceService.deleteWorkspace(id);

        verify(workspaceDao, times(1)).deleteWorkspace(id);
    }

    @Test
    public void testDeleteNonExistingWorkspace() {
        int id = 1;
        when(workspaceDao.getWorkspaceById(id)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            workspaceService.deleteWorkspace(id);
        });
    }

    @Test
    public void testGetAllWorkspaces() {
        Workspace workspace1 = new Workspace(1, "Workspace 1", true);
        Workspace workspace2 = new Workspace(2, "Workspace 2", false);
        List<Workspace> expectedWorkspaces = Arrays.asList(workspace1, workspace2);
        when(workspaceDao.getAllWorkspaces()).thenReturn(expectedWorkspaces);

        List<Workspace> actualWorkspaces = workspaceService.getAllWorkspaces();

        assertEquals(expectedWorkspaces.size(), actualWorkspaces.size());
        assertEquals(expectedWorkspaces.get(0), actualWorkspaces.get(0));
        assertEquals(expectedWorkspaces.get(1), actualWorkspaces.get(1));
    }
}
