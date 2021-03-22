import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INotificationEvent, defaultValue } from 'app/shared/model/notification-event.model';

export const ACTION_TYPES = {
  FETCH_NOTIFICATIONEVENT_LIST: 'notificationEvent/FETCH_NOTIFICATIONEVENT_LIST',
  FETCH_NOTIFICATIONEVENT: 'notificationEvent/FETCH_NOTIFICATIONEVENT',
  CREATE_NOTIFICATIONEVENT: 'notificationEvent/CREATE_NOTIFICATIONEVENT',
  UPDATE_NOTIFICATIONEVENT: 'notificationEvent/UPDATE_NOTIFICATIONEVENT',
  DELETE_NOTIFICATIONEVENT: 'notificationEvent/DELETE_NOTIFICATIONEVENT',
  SET_BLOB: 'notificationEvent/SET_BLOB',
  RESET: 'notificationEvent/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INotificationEvent>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type NotificationEventState = Readonly<typeof initialState>;

// Reducer

export default (state: NotificationEventState = initialState, action): NotificationEventState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NOTIFICATIONEVENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NOTIFICATIONEVENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NOTIFICATIONEVENT):
    case REQUEST(ACTION_TYPES.UPDATE_NOTIFICATIONEVENT):
    case REQUEST(ACTION_TYPES.DELETE_NOTIFICATIONEVENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NOTIFICATIONEVENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NOTIFICATIONEVENT):
    case FAILURE(ACTION_TYPES.CREATE_NOTIFICATIONEVENT):
    case FAILURE(ACTION_TYPES.UPDATE_NOTIFICATIONEVENT):
    case FAILURE(ACTION_TYPES.DELETE_NOTIFICATIONEVENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTIFICATIONEVENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTIFICATIONEVENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NOTIFICATIONEVENT):
    case SUCCESS(ACTION_TYPES.UPDATE_NOTIFICATIONEVENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NOTIFICATIONEVENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/notification-events';

// Actions

export const getEntities: ICrudGetAllAction<INotificationEvent> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NOTIFICATIONEVENT_LIST,
    payload: axios.get<INotificationEvent>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<INotificationEvent> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NOTIFICATIONEVENT,
    payload: axios.get<INotificationEvent>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INotificationEvent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NOTIFICATIONEVENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INotificationEvent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NOTIFICATIONEVENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INotificationEvent> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NOTIFICATIONEVENT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
