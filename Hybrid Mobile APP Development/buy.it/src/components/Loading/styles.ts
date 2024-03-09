import styled from 'styled-components/native';

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const LoadingText = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;

  max-width: 300px;

  margin-top: 30px;

  text-align: center;
`;

export const LoadIndicator = styled.ActivityIndicator.attrs(({ theme }) => ({
  size: '15px',
  color: theme.COLORS.WHITE,
}))``;
