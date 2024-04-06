import styled from 'styled-components/native';

export const ChipContainer = styled.View`
  background-color: ${({ theme }) => theme.COLORS.PRIMARY_DARK};
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 5px;
  align-self: flex-start;

  border-radius: 20px;
  padding: 5px 15px;
`;

export const ChipText = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.XS}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
  text-align: center;
`;
