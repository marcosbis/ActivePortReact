import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserService, defaultValue } from 'app/shared/model/user-service.model';

export const ACTION_TYPES = {
  FETCH_USERSERVICE_LIST: 'userService/FETCH_USERSERVICE_LIST',
  FETCH_USERSERVICE: 'userService/FETCH_USERSERVICE',
  CREATE_USERSERVICE: 'userService/CREATE_USERSERVICE',
  UPDATE_USERSERVICE: 'userService/UPDATE_USERSERVICE',
  DELETE_USERSERVICE: 'userService/DELETE_USERSERVICE',
  RESET: 'userService/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserService>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type UserServiceState = Readonly<typeof initialState>;

// Reducer

export default (state: UserServiceState = initialState, action): UserServiceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERSERVICE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERSERVICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_USERSERVICE):
    case REQUEST(ACTION_TYPES.UPDATE_USERSERVICE):
    case REQUEST(ACTION_TYPES.DELETE_USERSERVICE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_USERSERVICE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERSERVICE):
    case FAILURE(ACTION_TYPES.CREATE_USERSERVICE):
    case FAILURE(ACTION_TYPES.UPDATE_USERSERVICE):
    case FAILURE(ACTION_TYPES.DELETE_USERSERVICE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERSERVICE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERSERVICE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERSERVICE):
    case SUCCESS(ACTION_TYPES.UPDATE_USERSERVICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERSERVICE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/user-services';

// Actions

export const getEntities: ICrudGetAllAction<IUserService> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_USERSERVICE_LIST,
    payload: axios.get<IUserService>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IUserService> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERSERVICE,
    payload: axios.get<IUserService>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IUserService> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERSERVICE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserService> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERSERVICE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserService> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERSERVICE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
