import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVntuDownlinkPort, defaultValue } from 'app/shared/model/vntu-downlink-port.model';

export const ACTION_TYPES = {
  FETCH_VNTUDOWNLINKPORT_LIST: 'vntuDownlinkPort/FETCH_VNTUDOWNLINKPORT_LIST',
  FETCH_VNTUDOWNLINKPORT: 'vntuDownlinkPort/FETCH_VNTUDOWNLINKPORT',
  CREATE_VNTUDOWNLINKPORT: 'vntuDownlinkPort/CREATE_VNTUDOWNLINKPORT',
  UPDATE_VNTUDOWNLINKPORT: 'vntuDownlinkPort/UPDATE_VNTUDOWNLINKPORT',
  DELETE_VNTUDOWNLINKPORT: 'vntuDownlinkPort/DELETE_VNTUDOWNLINKPORT',
  RESET: 'vntuDownlinkPort/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVntuDownlinkPort>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type VntuDownlinkPortState = Readonly<typeof initialState>;

// Reducer

export default (state: VntuDownlinkPortState = initialState, action): VntuDownlinkPortState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_VNTUDOWNLINKPORT):
    case REQUEST(ACTION_TYPES.UPDATE_VNTUDOWNLINKPORT):
    case REQUEST(ACTION_TYPES.DELETE_VNTUDOWNLINKPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT):
    case FAILURE(ACTION_TYPES.CREATE_VNTUDOWNLINKPORT):
    case FAILURE(ACTION_TYPES.UPDATE_VNTUDOWNLINKPORT):
    case FAILURE(ACTION_TYPES.DELETE_VNTUDOWNLINKPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_VNTUDOWNLINKPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_VNTUDOWNLINKPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_VNTUDOWNLINKPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_VNTUDOWNLINKPORT):
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

const apiUrl = 'api/vntu-downlink-ports';

// Actions

export const getEntities: ICrudGetAllAction<IVntuDownlinkPort> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_VNTUDOWNLINKPORT_LIST,
    payload: axios.get<IVntuDownlinkPort>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IVntuDownlinkPort> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VNTUDOWNLINKPORT,
    payload: axios.get<IVntuDownlinkPort>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IVntuDownlinkPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VNTUDOWNLINKPORT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVntuDownlinkPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VNTUDOWNLINKPORT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVntuDownlinkPort> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VNTUDOWNLINKPORT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
