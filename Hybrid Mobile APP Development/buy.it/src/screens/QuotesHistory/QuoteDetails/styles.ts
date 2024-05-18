import styled from 'styled-components/native';

export const Container = styled.View`
  padding: 20px 0;
  border-bottom-width: 1px;
  border-color: ${({ theme }) => theme.COLORS.GRAY_400};
  display: flex;
  flex-direction: column;
  gap: 7px;
`;

export const Label = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  margin-bottom: 10px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const Value = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  color: ${({ theme }) => theme.COLORS.GRAY_300};
`;

export const BiggerValue = styled(Value)`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
`;

export const TimeAgo = styled.View`
  padding: 0 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 5px;
  margin-bottom: 20px;
`;

export const Subtitle = styled(Value)`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
`;

export const Price = styled(Value)`
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
`;

export const TextIcon = styled(TimeAgo)`
  padding: 0;
  margin: 0;
`;

export const Tags = styled.View`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 5px;
`;

export const Actions = styled(Tags)`
  gap: 20px;
  justify-content: center;
  margin-top: 20px;
  margin-bottom: 60px;
`;

export const TextIndicator = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme}) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme}) => theme.COLORS.GRAY_300};
  margin-top: 50px;
  text-align: center;
`;
