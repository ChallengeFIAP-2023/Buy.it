import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { MainRoutes } from '@screens/Main';
import { Text } from 'react-native';
import styled from 'styled-components/native';

const Tab = createBottomTabNavigator<MainRoutes>();

export const TabText = styled(Text)`
  margin-left: 20px;

  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const TabScreen = styled(Tab.Screen)`
  background-color: ${({ theme }) => theme.COLORS.PRIMARY};
`;
