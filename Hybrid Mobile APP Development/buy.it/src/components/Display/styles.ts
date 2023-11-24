import styled from 'styled-components/native';

export const DisplayContainer = styled.View`
  margin-bottom: 10px;
`;

export const Label = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
`;

export const Value = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  color: ${({ theme }) => theme.COLORS.GRAY_100};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
`;