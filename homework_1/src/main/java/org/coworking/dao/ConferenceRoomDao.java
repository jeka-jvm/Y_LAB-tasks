package org.coworking.dao;

import org.coworking.exceptions.ResourceNotFoundException;
import org.coworking.model.ConferenceRoom;

import java.util.List;

/**
 * Интерфейс для доступа к данным о конференц-залах.
 */
public interface ConferenceRoomDao {

    /**
     * Добавляет новый конференц-зал в хранилище.
     *
     * @param conferenceRoom объект конференц-зала для добавления
     */
    void addConferenceRoom(ConferenceRoom conferenceRoom);

    /**
     * Обновляет информацию о существующем конференц-зале.
     *
     * @param conferenceRoom обновленный объект конференц-зала
     * @throws ResourceNotFoundException если конференц-зал не найден
     */
    void updateConferenceRoom(ConferenceRoom conferenceRoom) throws ResourceNotFoundException;

    /**
     * Удаляет конференц-зал по указанному идентификатору.
     *
     * @param id идентификатор конференц-зала для удаления
     */
    void deleteConferenceRoom(int id);

    /**
     * Возвращает конференц-зал по его идентификатору.
     *
     * @param id идентификатор конференц-зала
     * @return объект конференц-зала или {@code null}, если конференц-зал не найден
     */
    ConferenceRoom getConferenceRoomById(int id);

    /**
     * Возвращает список всех конференц-залов.
     *
     * @return список всех конференц-залов
     */
    List<ConferenceRoom> getAllConferenceRooms();
}
