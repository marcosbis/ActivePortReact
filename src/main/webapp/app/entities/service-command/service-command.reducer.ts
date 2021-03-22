import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IServiceCommand, defaultValue } from 'app/shared/model/service-command.model';

export const ACTION_TYPES = {
  FETCH_SERVICECOMMAND_LIST: 'serviceCommand/FETCH_SERVICECOMMAND_LIST',
  FETCH_SERVICECOMMAND: 'serviceCommand/FETCH_SERVICECOMMAND',
  CREATE_SERVICECOMMAND: 'serviceCommand/CREATE_SERVICECOMMAND',
  UPDATE_SERVICECOMMAND: 'serviceCommand/UPDATE_SERVICECOMMAND',
  DELETE_SERVICECOMMAND: 'serviceCommand/DELETE_SERVICECOMMAND',
  SET_BLOB: 'serviceCommand/SET_BLOB',
  RESET: 'serviceCommand/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IServiceCommand>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ServiceCommandState = Readonly<typeof initialState>;

// Reducer

export default (state: ServiceCommandState = initialState, action): ServiceCommandState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SERVICECOMMAND_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SERVICECOMMAND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SERVICECOMMAND):
    case REQUEST(ACTION_TYPES.UPDATE_SERVICECOMMAND):
    case REQUEST(ACTION_TYPES.DELETE_SERVICECOMMAND):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SERVICECOMMAND_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SERVICECOMMAND):
    case FAILURE(ACTION_TYPES.CREATE_SERVICECOMMAND):
    case FAILURE(ACTION_TYPES.UPDATE_SERVICECOMMAND):
    case FAILURE(ACTION_TYPES.DELETE_SERVICECOMMAND):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECOMMAND_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SERVICECOMMAND):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SERVICECOMMAND):
    case SUCCESS(ACTION_TYPES.UPDATE_SERVICECOMMAND):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SERVICECOMMAND):
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

const apiUrl = 'api/service-commands';

// Actions

export const getEntities: ICrudGetAllAction<IServiceCommand> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECOMMAND_LIST,
    payload: axios.get<IServiceCommand>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IServiceCommand> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SERVICECOMMAND,
    payload: axios.get<IServiceCommand>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IServiceCommand> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SERVICECOMMAND,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IServiceCommand> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SERVICECOMMAND,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IServiceCommand> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SERVICECOMMAND,
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
