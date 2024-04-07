import styled from 'styled-components/native';

export const Container = styled.View`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 25px;

  margin: 16px 0;
`;

export const Tab = styled.TouchableOpacity.attrs({ activeOpacity: 1 })`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
`;

export const TabText = styled.Text<{ active: boolean }>`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme, active }) =>
    active
      ? theme.FONT_FAMILY.RALEWAY.BOLD
      : theme.FONT_FAMILY.RALEWAY.REGULAR};
  color: ${({ theme, active }) =>
    active ? theme.COLORS.WHITE : theme.COLORS.GRAY_100};
`;

export const BottomTab = styled.View<{ active: boolean }>`
  height: 4px;
  width: 30px;

  border-radius: 15px;

  background-color: ${({ theme, active }) =>
    active ? theme.COLORS.PRIMARY_LIGHT : theme.COLORS.PRIMARY_DARKER};
`;
