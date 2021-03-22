import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRealmIp, defaultValue } from 'app/shared/model/realm-ip.model';

export const ACTION_TYPES = {
  FETCH_REALMIP_LIST: 'realmIp/FETCH_REALMIP_LIST',
  FETCH_REALMIP: 'realmIp/FETCH_REALMIP',
  CREATE_REALMIP: 'realmIp/CREATE_REALMIP',
  UPDATE_REALMIP: 'realmIp/UPDATE_REALMIP',
  DELETE_REALMIP: 'realmIp/DELETE_REALMIP',
  RESET: 'realmIp/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRealmIp>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type RealmIpState = Readonly<typeof initialState>;

// Reducer

export default (state: RealmIpState = initialState, action): RealmIpState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REALMIP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REALMIP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_REALMIP):
    case REQUEST(ACTION_TYPES.UPDATE_REALMIP):
    case REQUEST(ACTION_TYPES.DELETE_REALMIP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_REALMIP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REALMIP):
    case FAILURE(ACTION_TYPES.CREATE_REALMIP):
    case FAILURE(ACTION_TYPES.UPDATE_REALMIP):
    case FAILURE(ACTION_TYPES.DELETE_REALMIP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_REALMIP_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_REALMIP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_REALMIP):
    case SUCCESS(ACTION_TYPES.UPDATE_REALMIP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_REALMIP):
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

const apiUrl = 'api/realm-ips';

// Actions

export const getEntities: ICrudGetAllAction<IRealmIp> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_REALMIP_LIST,
    payload: axios.get<IRealmIp>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IRealmIp> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REALMIP,
    payload: axios.get<IRealmIp>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRealmIp> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REALMIP,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRealmIp> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REALMIP,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRealmIp> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REALMIP,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
