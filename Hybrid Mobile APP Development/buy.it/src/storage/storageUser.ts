import AsyncStorage from '@react-native-async-storage/async-storage';

// Type import
import { User } from '@dtos/index';

// Storage import
import { USER_STORAGE } from '@storage/storageConfig';

export const storageUserSave = async (user: User) =>
  await AsyncStorage.setItem(USER_STORAGE, JSON.stringify(user));

export async function storageUserGet() {
  const storage = await AsyncStorage.getItem(USER_STORAGE);

  const user: User = storage ? JSON.parse(storage) : ({} as User);

  return user;
}

export const storageUserRemove = async () =>
  await AsyncStorage.removeItem(USER_STORAGE);
