import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEntityManager, defaultValue } from 'app/shared/model/entity-manager.model';

export const ACTION_TYPES = {
  FETCH_ENTITYMANAGER_LIST: 'entityManager/FETCH_ENTITYMANAGER_LIST',
  FETCH_ENTITYMANAGER: 'entityManager/FETCH_ENTITYMANAGER',
  CREATE_ENTITYMANAGER: 'entityManager/CREATE_ENTITYMANAGER',
  UPDATE_ENTITYMANAGER: 'entityManager/UPDATE_ENTITYMANAGER',
  DELETE_ENTITYMANAGER: 'entityManager/DELETE_ENTITYMANAGER',
  RESET: 'entityManager/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEntityManager>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type EntityManagerState = Readonly<typeof initialState>;

// Reducer

export default (state: EntityManagerState = initialState, action): EntityManagerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ENTITYMANAGER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ENTITYMANAGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ENTITYMANAGER):
    case REQUEST(ACTION_TYPES.UPDATE_ENTITYMANAGER):
    case REQUEST(ACTION_TYPES.DELETE_ENTITYMANAGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ENTITYMANAGER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ENTITYMANAGER):
    case FAILURE(ACTION_TYPES.CREATE_ENTITYMANAGER):
    case FAILURE(ACTION_TYPES.UPDATE_ENTITYMANAGER):
    case FAILURE(ACTION_TYPES.DELETE_ENTITYMANAGER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENTITYMANAGER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_ENTITYMANAGER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ENTITYMANAGER):
    case SUCCESS(ACTION_TYPES.UPDATE_ENTITYMANAGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ENTITYMANAGER):
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

const apiUrl = 'api/entity-managers';

// Actions

export const getEntities: ICrudGetAllAction<IEntityManager> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ENTITYMANAGER_LIST,
    payload: axios.get<IEntityManager>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IEntityManager> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ENTITYMANAGER,
    payload: axios.get<IEntityManager>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IEntityManager> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ENTITYMANAGER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEntityManager> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ENTITYMANAGER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEntityManager> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ENTITYMANAGER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
