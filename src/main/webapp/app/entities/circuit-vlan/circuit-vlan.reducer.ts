import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICircuitVlan, defaultValue } from 'app/shared/model/circuit-vlan.model';

export const ACTION_TYPES = {
  FETCH_CIRCUITVLAN_LIST: 'circuitVlan/FETCH_CIRCUITVLAN_LIST',
  FETCH_CIRCUITVLAN: 'circuitVlan/FETCH_CIRCUITVLAN',
  CREATE_CIRCUITVLAN: 'circuitVlan/CREATE_CIRCUITVLAN',
  UPDATE_CIRCUITVLAN: 'circuitVlan/UPDATE_CIRCUITVLAN',
  DELETE_CIRCUITVLAN: 'circuitVlan/DELETE_CIRCUITVLAN',
  RESET: 'circuitVlan/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICircuitVlan>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type CircuitVlanState = Readonly<typeof initialState>;

// Reducer

export default (state: CircuitVlanState = initialState, action): CircuitVlanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CIRCUITVLAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CIRCUITVLAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CIRCUITVLAN):
    case REQUEST(ACTION_TYPES.UPDATE_CIRCUITVLAN):
    case REQUEST(ACTION_TYPES.DELETE_CIRCUITVLAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CIRCUITVLAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CIRCUITVLAN):
    case FAILURE(ACTION_TYPES.CREATE_CIRCUITVLAN):
    case FAILURE(ACTION_TYPES.UPDATE_CIRCUITVLAN):
    case FAILURE(ACTION_TYPES.DELETE_CIRCUITVLAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CIRCUITVLAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_CIRCUITVLAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CIRCUITVLAN):
    case SUCCESS(ACTION_TYPES.UPDATE_CIRCUITVLAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CIRCUITVLAN):
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

const apiUrl = 'api/circuit-vlans';

// Actions

export const getEntities: ICrudGetAllAction<ICircuitVlan> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CIRCUITVLAN_LIST,
    payload: axios.get<ICircuitVlan>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ICircuitVlan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CIRCUITVLAN,
    payload: axios.get<ICircuitVlan>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICircuitVlan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CIRCUITVLAN,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICircuitVlan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CIRCUITVLAN,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICircuitVlan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CIRCUITVLAN,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
