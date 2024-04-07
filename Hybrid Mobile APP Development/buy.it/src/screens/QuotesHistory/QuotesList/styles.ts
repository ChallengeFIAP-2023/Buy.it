import styled from 'styled-components/native';

export const QuotesWrapper = styled.View`
  padding-bottom: 50px;
`;

export const Container = styled.View`
  display: flex;
  flex-direction: column;
  gap: 50px;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
`;

export const TextIndicator = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme}) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme}) => theme.COLORS.GRAY_300};
  text-align: center;
`;
